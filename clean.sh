#!/usr/bin/bash

echo 'Correction des apostrophes'
sed -i -E "s/([a-zA-Z])'([a-zA-ZàâäéèêëïîôöùûüÿæœÀÂÄÇÉÈÊËÎÏÔÖÙÛÜŸÆŒ])/\1’\2/" README.md days/day*.md

echo 'Suppression des espaces en fin de ligne'
sed -i 's/[[:space:]]*$//' README.md days/day*.md

echo 'Conversion des retours à la ligne'
unix2dos -q README.md days/day*.md
