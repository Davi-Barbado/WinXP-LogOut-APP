#!/bin/bash
cd shutdown_sound
rm path && touch path
echo "sound/$(ls sound)" >> path
./bin/bootsound
systemctl poweroff
