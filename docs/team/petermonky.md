---
layout: page
title: Peter Jung's Project Portfolio Page
---

### Project: Tinner

Tinner (anagram of Intern) is a desktop app for managing internship applications, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Tinner allows you to easily sort through and retrieve the relevant information faster than traditional GUI apps.

Given below are my contributions to the project.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=petermonky&breakdown=true)


* **New Features**:
  * Added the `Role` class and relevant tests to Tinner (Pull request [\#46](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/46))
    * What it does: Introduces a core construct to the application; a `Role` object is associated with a `Company` object in the application and is able to store information about an internship role
    * Justification: The `Role` class forms the foundation of the application; provides the ability for users to track, remind, and review their internships applications
    * Highlights: This change would inspire a sequence of changes in the UI, documentation, and testing relevant to the `Role` class
  * Updated the UI to display `Role` objects as stored Tinner (Pull request [\#79](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/79))
    * What it does: Visibly displays the list of `Role` objects associated with a `Company` object via a smaller scroll pane nested inside a `Company` card
    * Justification: The internally stored list of `Role` objects must be made visible to the user for tracking, otherwise the application would serve no purpose
    * Highlights: This change refactored the structure of the `RoleCard` class to better align with that of the `CompanyCard` for easier maintenance
  * Added star icon to indicate favourite status of `Company` object (Pull request [\#142](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/142))
    * What it does: Adds a star icon next to the name of a `Company` that has been favourited by the user via the `favourite` command
    * Justification: The star icon categorises specific companies that the user may be more interested in, enhancing the overall user experience
    * Highlights: This change was the first instance of integrating an image into the application, something that would reoccur with the addition of the command table in the help window
  * Created the reminder window (Pull requests [\#179](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/179), [\#186](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/186), [\#188](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/188), [\#293](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/293))
    * What it does: Allows the user to view a list of urgent reminders that fall within a set reminder window
    * Justification: This feature is necessary for users to be reminded of upcoming deadlines to their internship applications, a core value proposition of Tinner
    * Highlights: This change would not only be cosmetic but also entail refactoring of the `UniqueReminderList`


* **Project management**:
  * Routinely updated `Ui.png` for project website (Pull requests [\#42](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/42), [\#117](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/117))
  * Recorded a video demo of the application in v1.3


* **Enhancements to existing features**:
  * Updated storage behaviour for `Deadline` class for compatibility with testing (Pull request [\#68](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/68))
  * Refactored the `Name` class to `CompanyName` for clearer separation between the `Role` and `Company` classes (Pull request [\#80](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/80))
  * Restructured the `Role` class and other dependent classes to better model its usage as well as to ease its development (Pull request [\#99](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/99))
  * Overhauled UI completely to give a more personalised look to Tinner (Pull request [\#104](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/104))
  * Updated command parse error message for better readability (Pull request [\#264](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/264))
  * Added command table in help window (Pull request [\#294](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/294))


* **Documentation**:
  * User Guide:
    * Added documentation for the command summary (Pull request [\#24](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/24))
    * Cleaned up documentation by fixing typos, formatting, and screenshots (Pull request [\#101](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/101))
    * Updated purpose of UG and features (Pull request [\#203](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/203))
    * Unified all screenshots for aesthetics (Pull requests [\#306](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/306), [\#308](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/308))
  * Developer Guide:
    * Updated target user profile section (Pull request [\#23](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/23))
    * Converted documentation from `.docx` to `.md` (Pull request [\#23](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/23))
    * Updated documentation for `Logic` component (Pull request [\#158](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/158))
    * Added documentation for `deleteRole` command (Pull request [\#165](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/165))
    * Updated documentation for `UI` component (Pull request [\#275](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/275))
    * Added documentation for `setWindow` command and its use cases (Pull requests [\#275](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/275), [\#297](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/297))


* **Community**:
  * PRs reviewed (with non-trivial review comments:) (Pull requests: [\#64](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/64), [\#93](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/93), [\#182](https://github.com/AY2122S2-CS2103T-T17-1/tp/pull/182))
  * Reported bugs and suggestions for other teams in class during PE-D ([Issues](https://github.com/petermonky/ped/issues))
  * Participated in internal PE-D with other teams in tutorial group G11 ([Issues](https://github.com/AY2122S2-CS2103T-T17-2/tp/issues/240))
