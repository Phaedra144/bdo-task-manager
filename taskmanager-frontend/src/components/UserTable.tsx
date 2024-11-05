import { useEffect, useState } from 'react';
import { getUsers } from '../api/TaskManagerApiService';
import { User } from '../types/UserTasksTypes';
import { Link } from 'react-router-dom';

export const UserTable = () => {
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = () => {
    getUsers()
      .then((response) => {
        console.log(response);
        setUsers(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="container">
      <h1>Users</h1>
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Full name</th>
              <th>Email</th>
              <th>Address</th>
              <th>Tasks</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.fullName}</td>
                <td>{user.email}</td>
                <td>
                  {user.address?.city} {user.address?.zip}{' '}
                  {user.address?.street} {user.address?.streetNumber}
                </td>
                <td>
                  <Link to={`/users/${user.id}/tasks`}> Check tasks</Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};
