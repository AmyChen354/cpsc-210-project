# Accounts and Fresh keeper

## Introduction to the Usage

This project is designed for:

- Recording the **costs** of the items that the user bought each day
- Recording the **expiry dates** of the items that the user bought 
- Recording the **income** that the user made each day
- Computing the **total costs and income** for each day
- Computing the **net income** for each day
- Sending a **reminder** to the user half a month and a week before a certain item expires
- **Visualizing** the costs/income in a period of time selected by the user

## Who can use it

Anyone who wants to manage and learn about their costs and income in a certain period of time, and want to keep track 
of the item expiry dates.

## Intention of Design

I just moved out of my home last September and before that I used to live with my parents. I didn't have to keep our 
family account and worry about food expiring, as my parents took care of everything. Now it's just me alone, I need to
start taking responsibility of keeping track of my daily expenses and income. While some people are clever enough to 
use Excel for bookkeeping and visualizing their account books, for those who are not familiar with Excel or other 
relatively complex spreadsheet software, the desktop software I designed is able to present to users in the most simple 
and easy-understanding way, which greatly reduces the difficulty of use, so that even the elderly people who know a 
little about digital devices can easily do their bookkeeping on a computer. 

In addition, I also need to keep an eye on the freshness of some fruits and vegetables as they rot quickly if not eaten in time, while 
other foods can last longer, we will inevitably lose track of their expiration dates as time goes by. Therefore, the
software I designed also helps users keep track of the expiration dates of food they bought, and to remind users before the foods are about to expire to avoid unnecessary food waste.

## User Stories

- As a user, I want to be able to add an item with its name and cost to a list of items I bought in a day
- As a user, I want to be able to add a list of items in a day to a list of all dates
- As a user, I want to be able to add an income to a list of income on a date
- As a user, I want to be able to select a certain date and view the list of items/income on that day
- As a user, I want to be able to see the number of items/income in a list of a certain date
- As a user, I want to be able to change the name/expiry date of an item and the name of an income
- As a user, I want to be able to remove an item/income from a list of items/income of a certain date
- As a user, I want to be able to compute the total costs/income in a selected period of time(choose from mm/dd/yyyy to 
  mm/dd/yyyy)
- As a user, I want to be able to compute the net income in a selected period of time(net income = total income - total 
  costs)
- As a user, I want to be able to find the date on which an item was bought
- As a user, I want to be able to rank the items on their costs/expiry dates
- As a user, I want to be able to receive a reminder of expiring foods half a month and a week before they expire 
  respectively
- As a user, I want to be able to visualize the total costs/income in a selected period of time with different choices 
  of visualizations(scattered plot, histogram, pie chart)
