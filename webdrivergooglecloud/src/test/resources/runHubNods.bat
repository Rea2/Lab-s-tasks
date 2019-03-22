start "HUB" startHub.bat
echo "waiting 10 sec for running hub"
timeout 10
start "EDGEE NOD" starteEdgeNode.bat
timeout 2
start "CHROME NOD" startChromeNode.bat
timeout 2
start "FIREFOX NOD" startFirefoxNode.bat