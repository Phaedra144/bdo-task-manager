import { Outlet } from 'react-router-dom';
import { Header } from './Header';

export const TaskManager = () => {
  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
    </>
  );
};
