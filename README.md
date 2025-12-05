# OS-Desktop-Application-Advanced-Task-Manager-Java-Swing-AWT


A desktop-based **Advanced Task Manager** built using Java Swing, AWT, and PowerShell.  
This application works like a simplified Windows Task Manager, allowing users to **view, search, refresh, and terminate running processes** from a clean desktop GUI.

---

## 📌 Features

### 🔍 View Running Processes
Displays detailed information for every running process:
- **PID** (Process ID)
- **Process Name**
- **CPU Usage (%)**
- **Memory Usage (MB)**

### 🔎 Search Function
Search running processes by:
- Full/partial **process name**
- **PID**

Search dynamically filters table results.

### 🔁 Refresh Process List
Reloads the process list using PowerShell:
```powershell
Get-Process | Select-Object Name,Id,CPU,WorkingSet
❌ Kill (Terminate) Process
Terminate any selected process using:

powershell
Copy code
taskkill /PID <PID> /F
🖥️ Simple Clean UI
Built using:

Java Swing

AWT Layouts

JTable + JScrollPane

Organized 3-section layout (Search, Table, Controls)

📂 Project Structure
arduino
Copy code
Advanced-Task-Manager/
│
├- ProcessManager.java
│
└── README.md
🛠️ Requirements
Software Requirements
Windows OS

Java JDK 8 or higher

PowerShell enabled (default on Windows)

Technical Notes
The app uses:

java
Copy code
Runtime.getRuntime().exec("powershell.exe Get-Process...");
So PowerShell must be available in PATH.

▶️ How to Run the Application
1. Compile the Program
bash
Copy code
javac ProcessManager.java
2. Run the Program
bash
Copy code
java ProcessManager
Optional (Using IDE)
You can use any IDE:

IntelliJ IDEA

Eclipse

NetBeans

Open project → run ProcessManager.java.

🚀 How It Works
Fetching Processes
The app executes:

powershell
Copy code
Get-Process | Select-Object Name,Id,CPU,WorkingSet
Then parses:

CPU (may be empty for idle tasks → replaced by 0)

Memory (converted from bytes → MB)

Searching
Reloads full process list → removes rows that don’t match keyword.

Killing Processes
Uses Windows command:

powershell
Copy code
taskkill /PID <PID> /F
⚠️ Important Notes
Some system processes cannot be terminated without admin privileges.

Terminating critical processes may cause:

Program crashes

System instability

Forced reboot

CPU values may appear “0” for processes that have not consumed CPU recently (PowerShell behavior).

📄 License
This project is fully open-source.
You may use, modify, and distribute it freely for learning or personal development.

📬 Need More?
I can provide:

Dark themed UI

Modern Look-and-Feel

JAR packaging instructions

Icon-based UI upgrade

Search-as-you-type version

Linux/macOS compatible version

Just ask!

yaml
Copy code

---
