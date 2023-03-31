# Accounts and Fresh keeper

## Introduction to the Usage

This project is designed for:

- Recording the **costs** of the items that the user bought each day
- Recording the **expiry dates** of the items that the user bought 
- Computing the **total costs** for each day

## Who can use it

Anyone who wants to manage and learn about their costs in a certain period of time, and want to keep track 
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

- As a user, I want to be able to add an item with its name, cost, and expiry date to a list of items I bought on a 
  date
- As a user, I want to be able to add a date to an account book, which contains a list of all dates
- As a user, I want to be able to select a date and view the list of items on that selected date
- As a user, I want to be able to see the number of items bought on a selected date
- As a user, I want to be able to change the name/expiry date of an item  //TODO
- As a user, I want to be able to remove an item bought from a selected date
- As a user, I want to be able to compute the total costs on a selected date
- As a user, I want to be able to find the date on which an item was bought  //TODO
- As a user, I want to be able to rank the items on their costs/expiry dates  //TODO
- As a user, when I select the quit option from the application menu, I want to be reminded to save the state of all 
  items and dates I have created so far to file and have the option to do so or not
- As a user, when I start the application, I want to be given the option to load the account book and dates I have
  created so far from file

# Instructions for Grader

- You can add a new list to the account book by pressing the "New list" menu on the top
- You can add a new item to a date by pressing the "Existing list" menu on the top and then "Add new item"
- You can remove an item from a date by pressing the "Existing list" menu on the top and then "Remove an item"
- You can count the number of items on a date by pressing the "Existing list" menu on the top and then "Count the 
  number of items"
- You can view the items on a date by pressing the "Existing list" menu on the top and then "View items in the list"
- You can save the account book to local directory by pressing the "Save" menu on the top
- You can load the account book from local directory by pressing the "Load" menu on the top