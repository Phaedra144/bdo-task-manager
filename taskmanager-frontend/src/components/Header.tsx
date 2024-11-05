export const Header = () => {
  return (
    <header className="border-bottom border-light border-5 mb-5 p-2">
      <div className="container">
        <div className="row">
          <nav className="navbar navbar-expand-lg">
            <a className="navbar-brand ms-2 fs-2 fw-bold text-black">
              Task Manager
            </a>
            <div className="collapse navbar-collapse justify-content-center">
              <ul className="navbar-nav">
                <li className="nav-item fs-5">Users</li>
                <li className="nav-item fs-5">Tasks</li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </header>
  );
};
