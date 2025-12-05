# 🖥️ Advanced Task Manager (Java Desktop)

A lightweight **Java Swing–based Task Manager** that retrieves real-time system processes using **PowerShell** on Windows.  
This application allows you to:

✔ View PID, Process Name, CPU usage, Memory usage  
✔ Search processes by name or PID  
✔ Refresh the process list  
✔ Kill/terminate selected processes  
✔ Clean UI using Java Swing  

---

## 📸 Screenshot  
<img width="1326" height="731" alt="Screenshot 2025-11-26 122849" src="https://github.com/user-attachments/assets/cc573381-9ace-4a29-8dc3-54419b421a38" />
<img width="1318" height="741" alt="Screenshot 2025-11-26 122917" src="https://github.com/user-attachments/assets/c1947b5b-9038-4fb9-b637-e61faa87f6c6" />
<img width="1345" height="750" alt="Screenshot 2025-11-26 122959" src="https://github.com/user-attachments/assets/f69b73fa-d178-4a62-a6e4-37933555cc86" />


## 🚀 Features

### 🔍 Process Monitoring
- Fetches all running processes using PowerShell.
- Displays:
  - **PID**
  - **Process Name**
  - **CPU (%)**
  - **Memory (MB)** (converted from bytes)

### 🔎 Search
- Search by **process name** or **PID**
- Filters results dynamically

### 🔄 Refresh
- Reloads process list with updated CPU/Memory metrics

### ❌ Kill Process
- Uses Windows `taskkill` to terminate selected processes:
```

taskkill /PID <PID> /F

```

---

## 📂 Project Structure

```

ProcessManager/
│
├── src/
│   └── ProcessManager.java
│
└── README.md

```

---

## ⚙️ Requirements

### 🪟 Windows Only
This tool works only on **Windows OS**, because it uses:

```

powershell.exe Get-Process

````

### 🧰 Software Requirements
- **Java 8 or higher**
- **Windows PowerShell** (default on Windows 7+)

---

## 🛠️ How to Run

### 1️⃣ Clone this repository
```bash
git clone https://github.com/your-username/AdvancedTaskManager-Java.git
cd AdvancedTaskManager-Java
````

### 2️⃣ Compile the application

```bash
javac ProcessManager.java
```

### 3️⃣ Run the application

```bash
java ProcessManager
```

---

## 🧩 How It Works

### ▶ Fetching Processes

PowerShell command used:

```powershell
Get-Process | Select-Object Name,Id,CPU,WorkingSet
```

### 💾 Memory Conversion

WorkingSet (bytes) → MB:

```java
memBytes / (1024.0 * 1024.0)
```

### 🔎 Search Logic

Matches **keyword** with:

* Process name
* PID

### ❌ Killing a Process

Executes:

```bash
taskkill /PID <pid> /F
```

---

## 📌 Limitations

* Only works on **Windows OS**
* Requires admin privileges to terminate some processes
* CPU value from PowerShell is not real-time; it's cumulative

---

## 🧮 Future Enhancements

* Real-time CPU & RAM graph
* Sortable table columns
* Dark mode UI
* Cross-platform support using OSHI library

---

## 🤝 Contributing

Pull requests are welcome!
Feel free to open an issue for suggestions or bugs.

---
