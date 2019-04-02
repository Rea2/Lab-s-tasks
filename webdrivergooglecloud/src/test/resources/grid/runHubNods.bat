start "HUB" startHub.bat
echo "waiting 5 sec for running hub"
timeout 5
start "EDGEE NOD" starteEdgeNode.bat
timeout 2
start "CHROME NOD" startChromeNode.bat
timeout 2
start "FIREFOX NOD" startFirefoxNode.bat