import { useEffect, useState } from 'react';
import { FaEye, FaPencilAlt } from 'react-icons/fa';
import { Link, useParams } from 'react-router-dom';
import { getUsers } from '../api/TaskManagerApiService';
import { User } from '../types/UserTasksTypes';

export const UserTable = () => {
  const [users, setUsers] = useState<User[]>([]);
  const { userId } = useParams();

  useEffect(() => {
    fetchUsers();
  }, [userId]);

  const fetchUsers = () => {
    getUsers()
      .then((response) => {
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
              <th></th>
              <th></th>
              <th></th>
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
                  {user.tasks && user.tasks.length > 0 && (
                    <Link to={`/users/${user.id}/tasks`}> Check tasks</Link>
                  )}
                </td>
                <td>
                  <Link
                    to={`/users/${user.id}?no-modify=true`}
                    className="link-dark"
                  >
                    <FaEye />
                  </Link>
                </td>
                <td>
                  <Link to={`/users/${user.id}`} className="link-dark">
                    <FaPencilAlt />
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};
