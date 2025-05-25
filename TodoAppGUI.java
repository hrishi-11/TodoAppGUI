import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TodoAppGUI {
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, removeButton;

    public TodoAppGUI() {
        frame = new JFrame("To-Do List App");
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskField = new JTextField(20);
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        panel.add(removeButton, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a task.");
        }
    }

    private void removeTask() {
        int selected = taskList.getSelectedIndex();
        if (selected != -1) {
            taskListModel.remove(selected);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to remove.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoAppGUI::new);
    }
}
