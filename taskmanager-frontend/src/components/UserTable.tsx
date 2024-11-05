export const UserTable = () => {
  const users = [
    {
      id: 1,
      fullName: 'John Doe',
      email: 'john.doe@example.com',
      address: '123 Main St',
      tasks: [
        {
          id: 1,
          title: 'Task 1',
          description: 'Task 1 in details',
        },
        {
          id: 2,
          title: 'Task 2',
          description: 'Task 2 in details',
        },
      ],
    },
  ];

  return (
    <div className="container">
      <h1>User tasks</h1>
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
                <td>{user.address}</td>
                <td>
                  {user.tasks.map((task) => (
                    <span key={task.id}>{task.title} </span>
                  ))}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};
