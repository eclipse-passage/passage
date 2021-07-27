gpg --batch --import "${KEYRING}"
for fpr in $(gpg --list-keys --with-colons  | awk -F: '/fpr:/ {print $10}' | sort -u);
do
  echo -e "5\ny\n" |  gpg --batch --command-fd 0 --expert --edit-key $fpr trust;
done

# https://bugs.eclipse.org/bugs/show_bug.cgi?id=573866
mkdir -p ~/.gnupg
cat >> ~/.gnupg/gpg.conf <<EOL
use-agent 
pinentry-mode loopback
EOL
cat >> ~/.gnupg/gpg-agent.conf <<EOL
allow-loopback-pinentry
EOL
echo RELOADAGENT | gpg-connect-agent