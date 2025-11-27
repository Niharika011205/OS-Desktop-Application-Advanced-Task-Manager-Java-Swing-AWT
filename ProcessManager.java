import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProcessManager extends JFrame {

    private final DefaultTableModel tableModel;
    private final JTable processTable;
    private final JTextField searchField;

    public ProcessManager() {
        setTitle("Advanced Task Manager (Java Desktop)");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // UPDATED TABLE COLUMNS
        tableModel = new DefaultTableModel(new String[]{"PID", "Process Name", "CPU (%)", "Memory (MB)"}, 0);
        processTable = new JTable(tableModel);

        JScrollPane pane = new JScrollPane(processTable);
        add(pane, BorderLayout.CENTER);

        // TOP SEARCH
        JPanel topPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(clearBtn);
        add(topPanel, BorderLayout.NORTH);

        // BOTTOM PANEL
        JPanel bottomPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh");
        JButton killBtn = new JButton("Kill Process");

        bottomPanel.add(refreshBtn);
        bottomPanel.add(killBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load data
        
        SwingUtilities.invokeLater(this::loadProcesses);

        // button listeners
        refreshBtn.addActionListener(e -> loadProcesses());
        killBtn.addActionListener(e -> killSelectedProcess());
        searchBtn.addActionListener(e -> searchProcess());
        clearBtn.addActionListener(e -> loadProcesses());
    }

    // Load Processes with CPU + Memory
    private void loadProcesses() {
        try {
            tableModel.setRowCount(0);

            Process process = Runtime.getRuntime().exec(
                    "powershell.exe Get-Process | Select-Object Name,Id,CPU,WorkingSet");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            reader.readLine(); // Skip header lines
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+", 4);
                if (parts.length < 4) continue;

                String name = parts[0];
                String pid = parts[1];
                String cpu = parts[2];
                String mem = parts[3];

                try {
                    long memBytes = Long.parseLong(mem);
                    mem = String.format("%.2f", memBytes / (1024.0 * 1024.0)); // Convert bytes → MB
                } catch (Exception ignored) {
                    mem = "0";
                }

                if(cpu.equals("")) cpu = "0";

                tableModel.addRow(new Object[]{pid, name, cpu, mem});
            }
        } catch (IOException ex) {
            showError("Failed to load processes.");
        }
    }

    // Search
    private void searchProcess() {
        String keyword = searchField.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a process name or PID.");
            return;
        }

        loadProcesses(); // reload all

        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            String name = tableModel.getValueAt(i, 1).toString().toLowerCase();
            String pid = tableModel.getValueAt(i, 0).toString();

            if (!(name.contains(keyword) || pid.contains(keyword))) {
                tableModel.removeRow(i);
            }
        }
    }

    // Kill
    private void killSelectedProcess() {
        int row = processTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a process to kill.");
            return;
        }

        String pid = tableModel.getValueAt(row, 0).toString();

        try {
            Runtime.getRuntime().exec("taskkill /PID " + pid + " /F");
            JOptionPane.showMessageDialog(this, "Process terminated.");
            loadProcesses();
        } catch (IOException ex) {
            showError("Unable to terminate process.");
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProcessManager().setVisible(true));
    }
}