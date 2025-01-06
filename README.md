# 📚 **Whatsdaword - API Documentation**

Welcome to **Whatsdaword**, a **Reverse Dictionary API and Web Application** designed to search for words by their definitions and manage study sets efficiently. This project integrates **Spring Boot** for backend services, **MySQL** for database storage, and **Datamuse API** for word and definition retrieval. The application also provides endpoints for user and study set management, allowing seamless interaction and CRUD operations.

---

## 🚀 **How to Use the Endpoints**

Below is a **complete summary** of available API endpoints, their actions, and the corresponding HTTP methods:

| **Resource** | **Action**                    | **Method** | **Endpoint**                     |
|-------------|--------------------------------|-----------|----------------------------------|
| **User**    | Create User                    | **POST**   | `/api/users/create`              |
|             | Get All Users                  | **GET**    | `/api/users`                     |
|             | Get User by ID                 | **GET**    | `/api/users/{id}`                |
|             | Update User                    | **PUT**    | `/api/users/{id}`                |
|             | Delete User                    | **DELETE** | `/api/users/{id}`                |
| **StudySet**| Create Study Set               | **POST**   | `/api/studysets/create`          |
|             | Get Study Sets by User         | **GET**    | `/api/studysets/user/{userId}`   |
|             | Update Study Set               | **PUT**    | `/api/studysets/{id}`            |
|             | Delete Study Set               | **DELETE** | `/api/studysets/{id}`            |
| **Word**    | Search Word by Definition      | **GET**    | `/api/words/search?definition=`  |
|             | Add Word to Study Set          | **POST**   | `/api/words/add-to-studyset`     |
|             | Get Words from Study Set       | **GET**    | `/api/words/studyset/{studySetId}`|

---

## 🧑‍💼 **User Endpoints**

### 📥 **1. Create User**
- **Endpoint:** `POST /api/users/create`  
- **Description:** Create a new user.  
- **Sample Request Body:**
  ```json
  {
    "username": "user1",
    "password": "password123",
    "email": "user1@example.com"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  }
  ```

---

### 📤 **2. Get All Users**
- **Endpoint:** `GET /api/users`  
- **Description:** Retrieve all registered users.  
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "username": "user1",
      "email": "user1@example.com"
    },
    {
      "id": 2,
      "username": "user2",
      "email": "user2@example.com"
    }
  ]
  ```

---

### 📥 **3. Get User by ID**
- **Endpoint:** `GET /api/users/{id}`  
- **Description:** Retrieve a specific user by their ID.  
- **Sample Request:**  
  ```
  GET http://localhost:8082/api/users/1
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  }
  ```

---

### 🛠️ **4. Update User**
- **Endpoint:** `PUT /api/users/{id}`  
- **Description:** Update user details by ID.  
- **Sample Request Body:**
  ```json
  {
    "username": "updatedUser",
    "password": "newpassword123"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "username": "updatedUser",
    "email": "user1@example.com"
  }
  ```

---

### 🗑️ **5. Delete User**
- **Endpoint:** `DELETE /api/users/{id}`  
- **Description:** Delete a user by ID.  
- **Sample Request:**  
  ```
  DELETE http://localhost:8082/api/users/1
  ```
- **Sample Response:**
  ```
  204 No Content
  ```

---

## 📚 **StudySet Endpoints**

### 📥 **6. Create Study Set**
- **Endpoint:** `POST /api/studysets/create`  
- **Description:** Create a new study set for a specific user.  
- **Sample Request Body:**
  ```json
  {
    "name": "Study Set 1",
    "description": "A sample study set"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "name": "Study Set 1",
    "description": "A sample study set",
    "user": {
      "id": 1,
      "username": "user1"
    }
  }
  ```

---

### 📤 **7. Get Study Sets by User**
- **Endpoint:** `GET /api/studysets/user/{userId}`  
- **Description:** Retrieve all study sets belonging to a user.  
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "name": "Study Set 1",
      "description": "A sample study set"
    }
  ]
  ```

---

### 🛠️ **8. Update Study Set**
- **Endpoint:** `PUT /api/studysets/{id}`  
- **Description:** Update study set details.  
- **Sample Request Body:**
  ```json
  {
    "name": "Updated Study Set",
    "description": "An updated description"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "name": "Updated Study Set",
    "description": "An updated description"
  }
  ```

---

### 🗑️ **9. Delete Study Set**
- **Endpoint:** `DELETE /api/studysets/{id}`  
- **Description:** Delete a specific study set.  
- **Sample Response:**
  ```
  204 No Content
  ```

---

## 🔤 **Word Endpoints**

### 🔍 **10. Search Words by Definition**
- **Endpoint:** `GET /api/words/search?definition=happy`  
- **Description:** Fetch words matching a definition.  
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "word": "joyful",
      "definition": "adj Full of joy; happy."
    }
  ]
  ```

---

### 📥 **11. Add Word to Study Set**
- **Endpoint:** `POST /api/words/add-to-studyset?studySetId=1`  
- **Description:** Add a word to a specific study set.  
- **Sample Request Body:**
  ```json
  {
    "word": "elated",
    "definition": "adj Extremely happy and excited."
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "word": "elated",
    "definition": "adj Extremely happy and excited."
  }
  ```

---

### 📤 **12. Get Words from Study Set**
- **Endpoint:** `GET /api/words/studyset/{studySetId}`  
- **Description:** Retrieve all words in a study set.  
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "word": "elated",
      "definition": "adj Extremely happy and excited."
    }
  ]
  ```

---
