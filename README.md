# Text Analysis Project - Milestone 3

### Created by Michael Muscara
- **Major**: Computer Science
- **Year**: Sophomore at Fairfield University
- **Team Members**:
  - Michael Muscara
  - Kristopher Marte
  - Philip Casey-Leonard

## Project Overview
This project is a Java-based text analysis tool designed to preprocess and analyze articles on a given topic. The application gives the user an option to select one of four options. The four options include creating a file, adding a file, analyzing a topic of articles, and exiting the program. The program is very self explanatory as it will complete the corresponding action that the user chooses. If the user chooses to analyze the articles, the program will discover the overall sentiment of each article.

### Features:
1. **Create a Folder**: This feature creates a folder for the user.
2. **Add a File**: This tool adds a file to a folder specified by the user.
3. **Analyze Articles**: The program calculates the amount of positive and negative words used in order to find the overall sentiment of the articles

## How to Run the Program

### Prerequisites:
- Java Development Kit (JDK) version 8 or higher installed.
- A text editor or IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code).
- Basic knowledge of running Java programs in the command line or IDE.

### Running the Program:
1. **Download the Code**:
   - Clone or download the GitHub repository containing the project files.
     ```bash
     git clone https://github.com/your-username/TextAnalysisProject.git
     ```
     
2. **Open the Project**:
   - Open the project in your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse) or in a text editor.
     
3. **Run the Program**:
   - Compile and run the Main class
     
4. **Select an Option**:
   - Upon running the program, you will be prompted to select one of the following actions (create a folder, add a file, analyze articles, or exit)
   - The program will then complete the following action corresponding to what you chose. 
  
## Class Descriptions

### 1. **Article**:
   - This class handles filePaths to make File objects and retrieve data and basic statistics from each article.

### 2. **Frequency Sort**:
   - This class sorts elements of an ArrayList using bubble sorting.
   - This class is used to assist in the getWordFrequency() method in the Article class.

### 3. **Main**:
   - This is the entry point for the program. It provides the user interface giving them four options to choose from.
   - By analyzing the user's selection using a `Scanner`, the program does the corresponding action that the user chose.  

### 4. **Folder**: 
   - This class is used to create a folder, or add a file to an existing folder
   - Depending on whether or not the folder or file previously exist, they will be added to the specified locations. 

## UML Diagram
![UML Diagram](https://github.com/michaelmuscara/Semester-Project-Milestone-3/blob/main/UML%20Diagram%20Milestone%203.jpg)

The UML diagram illustrates the relationships between the main classes in this project: `Article`, `FrequencySort`, `Main`, and `Folder`. Each class is responsible for a specific task in the article preprocessing workflow.

## Output Results
![Output Results](https://github.com/michaelmuscara/SemesterProject-Milestone2/blob/main/output.png)

## Conclusion
This project successfully hits the Milestone 3 requirements by:
- Allowing the end user to interact with the application
- Providing a text-based user interface
- Having the ability to analyze articles and compare their overall sentiment

For any questions or issues with running the program, please reach out to Michael Muscara.
