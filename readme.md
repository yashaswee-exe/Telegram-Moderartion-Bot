# Telegram Sticker Cleaner Bot

A lightweight Telegram bot that automatically deletes stickers from group chats after a configurable delay.
It’s designed for groups that want to keep conversations tidy without manual moderation.

---

## ✨ Features

* Detects sticker messages in real time
* Deletes them after a fixed delay
* Works in group chats
* Minimal, dependency-light Java implementation

---

## 🛠 Tech Stack

* Java 17
* Telegram Bots API
* Maven

---

## ⚠️ Important Notes

* The bot only works in **groups**, not private chats
* It must have **admin rights** to delete messages
* Messages older than ~48 hours cannot be deleted (Telegram limitation)

---

## 📄 License

MIT License

---
