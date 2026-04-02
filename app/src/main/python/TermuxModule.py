import subprocess

def run_command(command):
    try:
        # Note: On Android, many shell commands might be restricted.
        # This is a basic implementation for demonstration.
        result = subprocess.run(command, shell=True, capture_output=True, text=True)
        if result.returncode == 0:
            return f"✅ Success:\n{result.stdout}"
        else:
            return f"❌ Error:\n{result.stderr}"
    except Exception as e:
        return f"⚠ Warning: {str(e)}"
