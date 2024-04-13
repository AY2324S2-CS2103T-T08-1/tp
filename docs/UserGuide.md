---
layout: page
title: User Guide
---
## Introduction

Streamline Your Care, Empower Your Patients: Welcome to the Healthcare Information Management Revolution!
Are you a doctor who has scrambled for a patient's medical information during a crucial consultation?
Or a clinic receptionist who's gotten frustrated from flipping through stacks of paper notes to recall a patient's phone number?
Those days are over!

Introducing **ImmuniMate**, our innovative platform designed to revolutionize the way healthcare professionals and staff manage patient information.
It's your secure, centralized hub where you can access all your patients' medical and personal details – allergies, visit history, medical conditions, you name it – just a few clicks away!

ImmuniMate is designed for healthcare professionals (doctors, nurses etc.) and healthcare staff (clinic receptionist etc.) longing to leverage the efficiencies of the 21st century, who:

* have basic experience in using computers
* have a computer with sufficient space (~40 MB)
* are comfortable with typing on a regular basis
* have no serious colour vision deficiencies (can differentiate red, yellow and green)

If you ticked all the points above, great! This user guide will equip you with the knowledge to unlock the full potential of ImmuniMate, from guidelines on how to use this guide, to getting started with the app, to tutorials on its comprehensive set of features.
Get ready to:

* Enhance Patient Care: Deliver faster, more informed consultations with easy access to vital information.
* Boost Efficiency: Save precious time by ditching paper records and streamlining your workflow.
* Revitalise Patients: Use organised information about your patients to offer them peronalised medical advice.

Ready to take patient service to the next level?
Time to ditch those stacks of paper and Excel spreadsheets, and embrace the future of healthcare.
Let's dive in and explore how ImmuniMate can transform your practice!

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## How to Use This Guide

ImmuniMate's user guide is optimised for ease of navigation, so that you can spend less time on the app, and more time on your patients.
The table of contents just before this section breaks down the guide into its constituent sections:

1. How to Use This Guide (this section)
   * descriptions on different sections of user guide
   * glossary on technical terms
2. Product Information
   * detailed description of product
   * summary of command and fields
3. Quick start
   * system requirements
   * how to download and get started on the app
4. Features
   * explains each feature with examples
   * common usage mistakes
5. Frequently Asked Questions
6. Known issues
   * descriptions on issues with ImmuniMate that have been spotted, but not fixed

Throughout this guide, there might be some terms that you might not be familiar with, and that's fine.
Here's a table of some technical terms you'll see further in the guide:

| Term                 | Definition                                                                                                                                                                                                                                                          |
|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Java**             | A programming language. Various versions can be downloaded from [here](https://www.oracle.com/sg/java/technologies/downloads/).                                                                                                                                     |
| **home folder**      | The main folder where all app activity can take place and files can be stored                                                                                                                                                                                       |
| **command terminal** | A text-based interface to your computer.<br>On Windows, this can be opened by pressing the Windows key, and searching for an app called "Command Prompt".<br> On iOS, this can be opened by pressing Command + Space, typing in "terminal", then pressing "Return". |
| `cd`                 | A Linux (operating system) command used to navigate to different folders in your command terminal. Stands for "change directory".<br>Linux tutorials can be found [here](https://ubuntu.com/tutorials/command-line-for-beginners#1-overview).                       |
| **GUI**              | Short for "Graphical User Interface". The digital interface in which user interact with graphical components, such as icons and buttons.                                                                                                                            |
| **CLI**              | Short for "Command Line Interface". A software mechanism you use to interact with the application using your keyboard.                                                                                                                                              |

## Product Information

ImmuniMate is a desktop application for healthcare professionals and staff to better store and manage their patients' personal and medical information.
It is optimised for a single user on a single computer, meaning that after downloading a copy on your computer and using it, your copy cannot be accessed through other computers over the Internet.

ImmuniMate is compatible with Windows, Linux and MacOS operating systems, and installation does not require any additional installers.
It has an eye-catching GUI to capture your attention, but despite that, all interactions with ImmuniMate happen through the command line interface (CLI).
This means each feature of ImmuniMate is only accessible through typing a command into the command box in its specified format, and pressing "Enter" to get a response.

The list of commands and their formats are specified below:

### Command summary

| Action                 | Format, Examples                                                                                                                                                                                                                                                                                                                                                      |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Create**             | `create <ic/NRIC> <n/Patient_Name> <hp/Phone_Number> a/<Address> <dob/Date_of_birth> <s/Sex> <st/Status> [e/Email] [c/Country_of_Nationality] [doa/Date_of_Admission] [bt/Blood type] [al/Allergies] [con/Condition] [sym/Symptom] [d/diagnosis]` <br> e.g., `create ic/S1234567A n/John Doe hp/98765432 a/311, Clementi Ave 2, #02-25 dob/1990-01-01 s/M st/PENDING` |
| **Read**               | `read <NRIC>` <br> e.g., `read S1234567A`                                                                                                                                                                                                                                                                                                                             |
| **Update**             | `update <NRIC> <Field>/<Content>` <br> e.g., `update S1234567A hp/91234567 e/jd123@example.com`                                                                                                                                                                                                                                                                       |
| **Find**               | `find n/<Part_of_name> <Part_of_name> <Part_of_name>` <br> e.g., `find n/Alex Bryan Charlie` <br> `find a/<Part_of_Address>, <Part_of_Address>, <Part_of_Address>, ...` <br> e.g., `find a/Serangoon, Geylang` <br> `find con/<Part_of_Condition>, <Part_of_Condition>, <Part_of_Condition>, ...` <br> e.g., `find con/Covid, Ebola`                                  |
| **Delete Patient**     | `delete <NRIC>`<br> e.g., `delete S1234567A`                                                                                                                                                                                                                                                                                                                          |
| **Delete Information** | `deleteinfo <NRIC> <Field>` <br> e.g., `deleteinfo S1234567A e/`                                                                                                                                                                                                                                                                                                      |
| **Add Visit**          | `addvisit ic/<NRIC> dov/<Date_of_Visit> sym/<Symptoms> d/<Diagnosis> st/<Status>` <br> e.g., `addvisit ic/S1234567A dov/2024-01-01 sym/Cough d/Covid st/UNWELL`                                                                                                                                                                                                       |
| **Check**              | `check <NRIC>` <br> e.g., `check S1234567A`                                                                                                                                                                                                                                                                                                                           |
| **Find Cluster**       | `cluster <cluster size> a/<Part_of_Address> d/<Diagnosis>` <br> e.g. `cluster 3 a/Serangoon d/dengue`                                                                                                                                                                                                                                                                 |
| **Clear**              | `clear`                                                                                                                                                                                                                                                                                                                                                               |
| **List**               | `list`                                                                                                                                                                                                                                                                                                                                                                |
| **Help**               | `help`                                                                                                                                                                                                                                                                                                                                                                |
| **Exit**               | `exit`                                                                                                                                                                                                                                                                                                                                                                |


The list of fields and their formats are specified below:

### Field summary

| Field                      | Prefix | Format                                                                                                                                                                             | Mandatory |
|----------------------------|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------|
| **Name**                   | `n/`   | The name of the patient. Case-insensitive alphanumeric characters and spaces, capped at 2,000,000,000 characters.                                                                  | Yes  |
| **NRIC**                   | `ic/`  | First character can be either S or T, followed by 7 digits, and the last character is an alphabetical letter, in accordance with Singapore NRIC formats. NRIC is case-insensitive. | Yes  |
| **Phone Number**           | `hp/`  | 8 digits.                                                                                                                                                                          | Yes  |
| **Address**                | `a/`   | Any text. Blank or empty text is not accepted.                                                                                                                                     | Yes  |
| **Date of birth**          | `dob/` | `yyyy-MM-dd` format.                                                                                                                                                               | Yes  |
| **Sex**                    | `s/`   | `M` or `F`, case-sensitive.                                                                                                                                                        | Yes  |
| **Status**                 | `st/`  | `PENDING`, `UNWELL`or `HEALTHY`, case-insensitive.                                                                                                                                 | Yes  |
| **Email**                  | `e/`   | Any valid email address of the form `local-part@domain`, case-sensitive.                                                                                                           | No   |
| **Country of nationality** | `c/`   | Any text. Blank or empty text is not accepted.                                                                                                                                     | No   |
| **Date of admission**      | `doa/` | `yyyy-MM-dd` format.                                                                                                                                                               | No   |
| **Blood type**             | `bt/`  | Any of `A+`, `A-`, `B+`, `B-`, `AB+`, `AB-`, `O+`, `O-`                                                                                                                            | No   |
| **Allergies**              | `al/`  | Any text. Blank or empty text is not accepted.                                                                                                                                     | No   |
| **Condition**              | `con/` | Any text. Blank or empty text is not accepted.                                                                                                                                     | No   |
| **Symptom**                | `sym/` | Any text. Blank or empty text is not accepted.                                                                                                                                     | No   |
| **Diagnosis**              | `d/`   | Any text. Blank or empty text is not accepted.                                                                                                                                     | No   |
| **Date of visit**          | `dov/` | `yyyy-MM-dd` format.                                                                                                                                                               | No   |


## Quick start

1. Ensure you have Java `11` or above installed in your Computer. Java can be downloaded from [here](https://www.oracle.com/sg/java/technologies/downloads/).

2. Download the latest `immuniMate.jar` from [here](https://github.com/AY2324S2-CS2103T-T08-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ImmuniMate.

4. Open a command terminal, `cd` into the folder you put the jar file in, and type `java -jar immuniMate.jar` and press "Enter" to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. The colored circle on the right of each patient's name is the status indicator. For more information about the status indicator, see [create](#creating-a-patient-profile-create).<br>
   ![Ui](images/GUI.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `create ic/S0123456A n/John Doe hp/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 dob/1990-01-01 s/M st/PENDING` : Adds a patient named `John Doe` to ImmuniMate.

   * `delete S0123456A` : Deletes all infromation of the patient with corresponding NRIC.

   * `clear` : Deletes all patients.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPERCASE` are the parameters to be supplied by the user.<br>
  e.g. in `update <NRIC> <Field>/CONTENT`, `CONTENT` is a parameter which can be used as `update S0123456A hp/87654321`.

* Items in angle brackets are mandatory.<br> 
  e.g. `<ic/NRIC>` must be given.

* Items in square brackets are optional.<br>
  e.g. `<ic/NRIC> [e/EMAIL]` can be used as `ic/S0123456A e/jd123@example.com` or as `ic/S0123456A`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/<Patient_Name> hp/<Phone_Number>`, `hp/<Phone_Number> n/<Patient_Name>` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<img src="images/helpMessage.png" alt="help message" width="500"/>

Format: `help`


### Creating a patient profile: `create`

Creates a patient profile in ImmuniMate. A patient profile refers to a record of a patient with a set of relevant information. For the complete field of information, refer to the [Field summary](#field-summary) at the end of this User Guide.

Format: `create ic/<NRIC> n/<Patient_Name> hp/<Phone_Number> a/<Address> dob/<Date_of_birth> s/<Sex> st/<Status> [e/Email] [c/Country_of_Nationality] [doa/Date_of_Admission] [bt/Blood type] [al/Allergies] [con/Condition] [sym/Symptom] [d/diagnosis]`

* All mandatory fields must be provided. Refer to the [Field Summary](#field-summary) for a list of mandatory and optional fields, and their formats.
* The unique identifier for each patient is the NRIC. The new NRIC must not already exist in the system.
* The status of the patient is indicated by a colored circle on the right of the patient's name. The color of the circle corresponds to the status of the patient. The status can be `PENDING` (yellow), `UNWELL`(red) or `HEALTHY`(green).

Examples:
* `create ic/S1234567A n/John Doe hp/98765432 a/311, Clementi Ave 2, #02-25 dob/1990-01-01 s/M st/PENDING`
* `create ic/S0123456A hp/87654321 a/311, Clementi Ave 2, #02-25 dob/1990-01-01 s/F st/PENDING e/janed@example.com bt/A+ n/Jane Doe`, 

Common mistakes:
* `create n/John Doe hp/98765432 a/311, Clementi Ave 2, #02-25 dob/1990-01-01 s/M st/PENDING` (missing NRIC)
* `create ic/S1234567A n/John Doe hp/98765432 a/311, Clementi Ave 2, #02-25 dob/1990-2-30 s/M st/PENDING` (Wrong date format)

### Listing all patients : `list`

Shows all patients in ImmuniMate.

Format: `list`

### Read specific patients : `read`

Shows all profile details of patient with corresponding NRIC.

Format: `read <NRIC>`
* The NRIC must follow the correct format specified in [Field Summary](#field-summary).

Examples:
* `read S1234567A`
  
Common mistakes:
* `read S12345678` (wrong NRIC format)


### Updating a patient's profile : `update`

Updates information of a patient with an existing profile in ImmuniMate.

Format: `update <NRIC> <Field>/CONTENT`

* Updates information of the patient with corresponding NRIC. 
* At least one of the fields must be provided.
* NRIC cannot be updated, all other values can be updated.
* Existing values will be updated to the input values.
* Refer to the [Field Summary](#field-summary) for a list of fields and their formats.

Examples:
*  `update S1234567A hp/91234567 e/jd123@example.com`
    * Updates the phone number and email address of the corresponding patient to be `91234567` and `jd123@example.com` respectively.
*  `update S0123456A a/123 Serangoon Road`
    * Updates the address of the corresponding patient to be `123 Serangoon Road`.

Common mistakes:
* `update S1234567A ic/T1234567A` (NRIC cannot be updated)
* `update S1234567A` (no field specified)

### Finding patients by name: `find`

Finds patients whose names contain any of the given keywords.

Format: `find n/<NAME> [NAME] [NAME] ...`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name field in each patient profile is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.
* Names can be separated with any number of spaces.

Examples:
* `find n/John`
  * Returns `John Major` and `John Doe`.
* `find n/alex david`
  * Returns `Alex Yeoh`, `David Li`.<br>
    <img src="images/findAlexDavid.png" alt="result for 'find alex david'" width="800"/>


### Finding patients by address: `find`

Finds patients whose addresses contain any of the given keywords.

Format: `find a/[LOCATION], [LOCATION], [LOCATION], ...`

* The search is case-insensitive. e.g. `serangoon` will match `Serangoon`.
* The order of the keywords does matter. e.g. `Clementi Ave` will not match `Ave Clementi`.
* Only the address field in each patient profile is searched.
* Partial words will be matched e.g. `Clem` will match `Clementi`.
* Patients matching at least one keyword will be returned (i.e. `OR` search). 
  e.g. `Clementi, Serangoon` will return patients with address containing `Serangoon` or `Clementi`.
* Locations must be separated by commas, and whitespaces before and after each location will be ignored.

Examples:
* `find a/Geylang`
  * Returns `Alex Yeoh`.
* `find a/geylang, serangoon, choa chu kang`
  * Returns `Alex Yeoh`, `Bernice Yu`, `David Li`.
* `find a/Serangoon`
  * Returns `Bernice Yu`, `David Li`.<br>
    <img src="images/findSerangoon.png" alt="result for 'find serangoon'" width="800"/>


### Finding patients by condition: `find`

Finds patients whose conditions contain any of the given keywords.

Format: `find con/[CONDITION], [CONDITION], [CONDITION], ...`

* The search is case-insensitive. e.g. `covid` will match `Covid`.
* The order of the keywords does matter. e.g. `Stomach Flu` will not match `Flu Stomach`.
* Only the condition is searched.
* Partial words will be matched e.g. `Cov` will match `Covid`.
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Covid, Dengue` will return patients with address containing `Covid` or `Dengue`.
* Conditions must be separated by commas, and whitespaces before and after each condition will be ignored.

Examples:
* `find con/covid, dengue, ebola`

Common mistakes:
* `find ic/S1234567X` (only condition, name and address can be searched with find)
  * If you would like to find a person with NRIC, use the `read` command instead.

### Deleting a patient : `delete`

Deletes the specified patient from ImmuniMate.

Format: `delete <NRIC>`

* Deletes the profile of the patient with the corresponding NRIC.
* The NRIC must follow the correct format as specified in [Field summary](#field-summary).

Examples:
* `delete S1234567A`
  * deletes patient uniquely identified by NRIC S1234567A.

Common mistakes:
* `delete S12345678` (NRIC must be in the correct format)
* `delete` (NRIC must be provided)

### Deleting information of a patient : `deleteinfo`

Deletes specified optional information from the specified person from ImmuniMate.

Format: `deleteinfo <NRIC> <Field>`

* Deletes specified information of the patient with corresponding NRIC.

Examples:
* `deleteinfo S1234567A e/`
  * Deletes the email of patient uniquely identified by NRIC S1234567A.
* `deleteinfo S0123456A e/ bt/ c/`
  * Deletes the email, blood type and country of nationality of patient uniquely identified by NRIC S0123456A.

Common mistakes:
* `deleteinfo S1234567A abc/` (a valid optional field from the [Field Summary](#field-summary) must be provided)
* `deleteinfo S1234567A` (a field must be provided)
* `deleteinfo S1234567A ic/` (mandatory fields cannot be deleted)
  * If mandatory fields are to be changed, the `update` command should be used.

### Add patient's visit to history : `addvisit`

Adds visit to patient history. 

Format: `addvisit ic/<NRIC> dov/<Date_of_Visit> sym/<Symptoms> d/<Diagnosis> st/<Status>`

* NRIC must be that of a patient already in ImmuniMate.

Examples:
* `addvisit ic/S1234567A dov/2024-01-01 sym/Cough d/Covid st/UNWELL`
  * Adds a visit to history of patient uniquely identified by NRIC S1234567A. During this visit on 2024-01-01, the patient was having a cough and was diagnosed to be unwell with Covid.
* `addvisit ic/S0123456A dov/2024-02-02 sym/Fever,Rashes d/Dengue st/PENDING`
  * Adds a visit to history of patient uniquely identified by NRIC S0123456A. During this visit on 2024-02-02, the patient had a fever and rashes. The doctor suspects the patient has dengue, but is unable to come to a conclusion, hence the `PENDING` status.

Common mistakes:
* `addvisit ic/S7654321X dov/2024-01-01 sym/Cough d/Covid st/` (NRIC must belong to a person existing in the system)
* `addvisit ic/S1234567A a/#101 Hougang Ave` (fields other than date of visit, symptoms, diagnosis and status cannot be added)
### Check patient history : `check`

Checks all visits in patient history.

Format: `check <NRIC>`
* NRIC must be that of a patient already in ImmuniMate.

Example:
* `check S1234567A`
  * Displays all visits in history of patient uniquely identified by NRIC S1234567A.

Common mistakes:
* `check S12345678` (NRIC must be in the correct format, and must exist in the system)

### Cluster finding : `cluster`

Shows whether or not the number of people **unwell** with the illness given (diagnosis in profile) in the location given is at least the integer given, and lists the people there with the illness. 

Format: `cluster <CLUSTER SIZE> a/<LOCATION> d/<DIAGNOSIS>`

* The search is case-insensitive. e.g. `serangoon` will match `Serangoon`.
* Only one location and diagnosis is searched.
* Location and diagnosis cannot be empty.
* Cluster size must be between 1 and 2,000,000,000.
* Partial words will be matched e.g. `Clem` will match `Clementi`, `deng` will match `dengue`.

Example:
* `cluster 3 a/Serangoon d/dengue`
  * Shows if there are at least 3 people unwell with dengue in locations with the substring "Serangoon". 

Common mistakes:
* `cluster 3 a/S d/dengue` (address should be a meaningful word or phrase indicative of a location in Singapore)
* `cluster 3 a/Serangoon` (diagnosis must be provided)
* `cluster 3 d/dengue` (diagnosis must be provided)
* `cluster 0 a/Serangoon d/dengue` (a positive cluster size must be provided)
* `cluster 30.5 a/Serangoon d/dengue` (an integer cluster sizet must be provided)

### Clearing all entries : `clear`

Clears all profiles from ImmuniMate.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Command History
ImmuniMate allows you to navigate through your previous commands so you can easily reuse them without having to
retype them entirely. To navigate through the Command History, use the Up Arrow Key to view a previous command, and use the Down
Arrow Key to view the next command. The Up and Down Arrow Keys can be found on the Arrow Keys.

**Notes:**
1. The Command History only saves valid commands, it does not save commands that were unsuccessful
2. The Command History is temporary and will not be stored in between sessions. When you close an instance of ImmuniMate, your Command History is cleared.

### Saving the data

ImmuniMate data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ImmuniMate data are saved automatically as a JSON file `[JAR file location]/data/immunimate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ImmuniMate will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause ImmuniMate to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

### Update patient's visit in history `[coming in v2.0]`

### Infection cluster tracking `[coming in v2.0]`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ImmuniMate home folder.

**Q**: I entered a command and don't want to type it again, how can I go back to it? <br>
**A**: Use the up and down arrow keys on the keyboard to navigate your Command History. More information is in the Command History section above. 

**Q**: I added a new Patient Visit to my patient, but it doesn't seem to update the Patient information. Is something wrong? <br>
**A**: No, this is intended behaviour as we want to afford more flexibility to GPs. Automatically updating the patient field based
on recent visits might result in accidentally overriding intended data. 

**Q**: My name has dashes, slashes, commas, or apostrophes, I can't input my name in the create command. <br>
**A**: This is intended behaviour. Our name field aims to adhere to ICA guidelines.
If your name has any special characters, kindly remove them for compliance. For example, "Lee Chi-Geng, Bryan" can be changed into "Lee Chi Geng Bryan".

**Q**: Help, I can't add multiple patient visits a day!<br>
**A**: This is intended behaviour. Patients are assumed to only have a single visit a day.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **When using the `cluster` command**, search is purely based on text, which means inputting "ave" will find all patients whose addresses contain"ave", although they do not form a cluster. We suggest users to input text indicative of location, such as "Hougang".
3. **The `email` field** is case-sensitive, but in practical usage, email is case-insensitive.
4. **The `country` field** does not limit the input to alphabetical characters and is case-sensitive, which may lead to incorrect data entry.
