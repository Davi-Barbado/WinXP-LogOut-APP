import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
	
public class Main implements ActionListener{
		static JButton shutdown;
		static JButton reboot;
		static JButton suspend;
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Log Out");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,120);	
		frame.setLayout(null);
		frame.setResizable(false);
		Main app = new Main();
		shutdown = new JButton();
		shutdown.setBounds(0,0,100,100);
		shutdown.addActionListener(app);
		ImageIcon shutdownico = new ImageIcon("shutdown.png");
		shutdown.setIcon(shutdownico);
		reboot = new JButton();
		reboot.setBounds(100,0,100,100);
		ImageIcon rebootico = new ImageIcon("reboot.png");
		reboot.setIcon(rebootico);
		reboot.addActionListener(app);
		suspend = new JButton();
		suspend.setBounds(200,0,100,100);
		ImageIcon suspendico = new ImageIcon("suspend.png");
		suspend.setIcon(suspendico);
		suspend.addActionListener(app);
		frame.add(shutdown);
		frame.add(reboot);
		frame.add(suspend);	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		List<String> command = new ArrayList<>();
		command.add("bash");
		command.add("-c");
		if (e.getSource()==shutdown){
			command.add("./shutdown_sound/run.sh");
		}
		else if (e.getSource()==reboot){
			command.add("systemctl reboot");
		}
		else if (e.getSource()==suspend){
			command.add("systemctl suspend");
		}
		try{
				ProcessBuilder pb = new ProcessBuilder(command);
				
				pb.redirectErrorStream(true);
				
				Process process = pb.start();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				int exitCode = process.waitFor();
				//System.out.println("Process End with the exit code: " + exitCode);
				
			}
			catch (Exception err) {
				err.printStackTrace();
			}
	}
}
