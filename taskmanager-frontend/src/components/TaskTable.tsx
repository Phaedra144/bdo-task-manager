export const TaskTable = () => {

  const tasks = [
    {
      id: 1,
      title: 'Task 1',
      description: 'Description 1',
    },
    {
      id: 2,
      title: 'Task 2',
      description: 'Description 2',
    },
    {
      id: 3,
      title: 'Task 3',
      description: 'Description 3',
    },
  ];

  return (
    <div className="container">
      <h1>Tasks</h1>
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Title</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            {tasks.map((task) => (
              <tr key={task.id}>
                <td>{task.id}</td>
                <td>{task.title}</td>
                <td>{task.description}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};
