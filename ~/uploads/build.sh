#!/bin/bash

mvn -U clean package -pl bundles/prma-tibd-bundle -am

if [ $? -eq 0 ]
then
  osascript -e 'display notification "SUCCESS" with title "Maven" sound name "Funk"'
  exit 0
else
  osascript -e 'display notification "FAILED" with title "Maven" sound name "Sosumi"'
  exit 1
fi