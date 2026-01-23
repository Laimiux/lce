# Signing Configuration

## Setup

1. **Export your GPG secret key:**
   ```bash
   gpg --export-secret-keys --armor YOUR_KEY_ID > /path/to/secring.gpg
   ```

2. **Get your Sonatype user tokens:**
   - Go to https://central.sonatype.com/usertoken
   - Click "Generate User Token"
   - Save the username and password

3. **Create `export_signing.sh`:**
   ```bash
   #!/bin/bash

   # Configuration
   SIGNING_KEY_FILE="/path/to/secring.gpg"
   SIGNING_KEY_ID="xxxxxxxx"  # Last 8 chars of your GPG key ID
   SIGNING_KEY_PASSWORD="your_gpg_passphrase"

   # Maven Central credentials (Sonatype user tokens)
   export ORG_GRADLE_PROJECT_mavenCentralUsername="your_token_username"
   export ORG_GRADLE_PROJECT_mavenCentralPassword="your_token_password"

   # GPG signing configuration
   export ORG_GRADLE_PROJECT_signingInMemoryKey=$(cat "$SIGNING_KEY_FILE")
   export ORG_GRADLE_PROJECT_signingInMemoryKeyId="$SIGNING_KEY_ID"
   export ORG_GRADLE_PROJECT_signingInMemoryKeyPassword="$SIGNING_KEY_PASSWORD"

   echo "Signing environment variables exported successfully"
   ```

4. **Make it executable:**
   ```bash
   chmod +x export_signing.sh
   ```

## Usage

Run the upload script:
```bash
./.buildscript/upload_archives.sh
```

The script automatically sources `export_signing.sh` to set up signing.
