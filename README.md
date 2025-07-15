# Process Scheduler GUI in Java

This project is a **Graphical CPU Scheduler Simulator** that visualizes how the **Shortest Job First (SJF)** algorithm schedules tasks in an operating system. Built using Java Scene builder, it provides an interactive graphical interface for adding processes and observing how they are scheduled in a Gantt chart.

The tool is designed not only as a simulation of SJF but also as a **foundation for building more advanced real-time scheduling visualizers**. With its modular structure, developers can extend it to simulate and visualize:
- Real-time scheduling algorithms like Rate Monotonic Scheduling (RMS) or Earliest Deadline First (EDF)
- Preemptive versions of SJF or Priority Scheduling
- OS-level resource management scenarios

This makes it an excellent educational tool for operating systems students and a strong base for research-oriented scheduling experiments.
This project is a **Shortest Job First (SJF) CPU Scheduling Algorithm Visualizer**, built using **Java Schene Builder**. It allows users to input processes, calculate scheduling, and visualize the resulting **Gantt chart** dynamically.

## 🚀 Features

- GUI interface to input and manage processes
- Implements **SJF (non-preemptive)** scheduling algorithm
- Visualizes the schedule using a custom Gantt chart
- Displays detailed process timing in a table
- Modular and extensible object-oriented design

## 📂 Project Structure

| File/Class              | Purpose |
|-------------------------|---------|
| `Main.java`             | Launches the application |
| `Process.java`          | Represents a process with burst time, ID, etc. |
| `Scheduler.java`        | Base class for scheduling logic |
| `SJF_Scheduler.java`    | Implements the Shortest Job First algorithm |
| `SJFSchedulerGUI.java`  | Handles the main window and GUI logic |
| `GanttChart.java`       | Panel for drawing the Gantt chart |
| `GanttChartCell.java`   | Represents individual blocks in the Gantt chart |
| `StartController.java`  | Controls transition from input to scheduling |
| `TableController.java`  | Manages the process table and statistics |

## 🛠️ Technologies

- Java (JDK 8+)
- Schene Builder (GUI Framework)
- Object-Oriented Programming

## 📷 Screenshots

> <img width="1278" height="797" alt="image" src="https://github.com/user-attachments/assets/2d596527-4e82-4fc8-9a53-9975817f0eff" />


## 🏁 How to Run

   Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SJF-Scheduler-GUI.git
   cd Release
   SJF_Schedular.exe
   ```

## 📄 License

This project is open source and free to use under the [MIT License](LICENSE).

---

> Developed with ❤️ for Operating Systems and CPU Scheduling visualization.
