import React from "react";

// Extracted UserItem component
function UserItem({ user }) {
  return (
    <li>
      <span>{user.name}</span> — <span>{user.email}</span>
    </li>
  );
}


function UserList() {
  
  const users = [
    { id: 1, name: "Alice", email: "alice@email.com" },
    { id: 2, name: "Bob", email: "bob@email.com" },
    { id: 3, name: "Charlie", email: "charlie@email.com" },
  ];

  return (
    <div style={{ maxWidth: 400, margin: "2rem auto", padding: 16 }}>
      <h2>User List</h2>
      <ul>
        {}
        {users.map((user) => (
          <UserItem key={user.id} user={user} />
        ))}
      </ul>
    </div>
  );
}

export default UserList;
