import java.awt.*;
import javax.swing.*;

public class TodoList extends JFrame {
    private JTextField taskField;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JButton addButton, completeButton, deleteButton;

    public TodoList() {
        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Create GUI components
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        taskField = new JTextField();
        addButton = new JButton("Add Task");
        addButton.addActionListener(e -> addTask());
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskCellRenderer());
        taskList.addListSelectionListener(e -> updateButtons());
        JScrollPane scrollPane = new JScrollPane(taskList);

        completeButton = new JButton("Complete Task");
        completeButton.addActionListener(e -> completeTask());
        deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(e -> deleteTask());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);

        // Layout the components
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        }
    }

    private void completeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            String task = taskListModel.get(selectedIndex);
            taskListModel.set(selectedIndex, "[✓] " + task);
            taskList.setSelectedIndex(selectedIndex);
            updateButtons();
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            taskListModel.remove(selectedIndex);
            updateButtons();
        }
    }

    private void updateButtons() {
        int selectedIndex = taskList.getSelectedIndex();
        completeButton.setEnabled(selectedIndex >= 0 && !taskListModel.get(selectedIndex).startsWith("[✓]"));
        deleteButton.setEnabled(selectedIndex >= 0);
    }

    private static class TaskCellRenderer extends JLabel implements ListCellRenderer<String> {
        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            setOpaque(true);
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoList::new);
    }
}