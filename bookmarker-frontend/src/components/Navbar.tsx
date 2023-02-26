import ColorPalette from "./Theme";

interface NavbarProps {
  items?: { href: String; name: String }[];
}

const Navbar = ({ items }: NavbarProps) => {
  let navbarItems: any = items?.map((item, index) => {
    return (
      <li key={index} className="nav-item">
        <a className="nav-link" href={item.href.toString()}>
          {item.name}
        </a>
      </li>
    );
  });
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div style={{ marginLeft: 20 }}>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          height="24"
          viewBox="0 0 24 24"
          width="24"
          fill={ColorPalette.DarkTheme.primaryColor}
          style={{ paddingBottom: 5, marginRight: 3 }}
        >
          <path d="M0 0h24v24H0z" fill="none" />
          <path d="M17 3H7c-1.1 0-1.99.9-1.99 2L5 21l7-3 7 3V5c0-1.1-.9-2-2-2z" />
        </svg>
        <a className="navbar-brand" href="/">
          Bookmarks
        </a>
      </div>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarText"
        aria-controls="navbarText"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarText">
        <ul className="navbar-nav ms-auto" style={{ marginRight: 20 }}>
          {navbarItems}
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
