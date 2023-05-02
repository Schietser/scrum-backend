 

# Optional: Needed to run a remote script the first time

Set-ExecutionPolicy RemoteSigned -Scope CurrentUser

# Download and run the script

Invoke-RestMethod get.scoop.sh | Invoke-Expression

# Install extra buckets (repositories)

scoop bucket add extras

scoop bucket add java

scoop bucket add versions

# Install required applications

scoop install redis redis-tui mongodb mongosh mongodb-compass

Set-Env -Machine -Name ":skull:" -Value "💀"