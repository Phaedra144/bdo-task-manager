import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getTasksByUserId } from '../api/TaskManagerApiService';
import { Task } from '../types/UserTasksTypes';

export const TaskTable = () => {
  const params = useParams();

  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    fetchTasks();
  }, []);

  const fetchTasks = () => {
    if (params.userId) {
      getTasksByUserId(Number.parseInt(params.userId))
        .then((response) => {
          console.log(response);
          setTasks(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

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
