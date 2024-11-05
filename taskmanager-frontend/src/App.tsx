import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { TaskManager } from './components/TaskManager';
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
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
