import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { TaskManager } from './components/TaskManager';
import { TaskTable } from './components/TaskTable';
import { UserTable } from './components/UserTable';

const router = createBrowserRouter([
  {
    element: <TaskManager />,
    children: [
      {
        path: '/',
        element: <UserTable />,
      },
      {
        path: '/users',
        element: <UserTable />,
      },
      {
        path: '/users/:userId/tasks',
        element: <TaskTable />,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
