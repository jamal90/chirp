import { useState } from "react";
import { Link } from "react-router-dom";

const NavBar = () => {

    const[currPath, setPath] = useState("/")

    // todo - take initial state using useEffect

    const navigate = (to) => {
        setPath(to)
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark text-light">
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarText">
                <ul className="navbar-nav mr-auto">
                    <li className={`nav-item ${currPath == '/' ? 'active': ''}`} onClick={() => navigate("/")}>
                        <Link className="nav-link" to="/web/">My Tweets</Link>
                    </li>
                    <li className={`nav-item ${currPath == '/feeds' ? 'active': ''}`}  onClick={() => navigate("/feeds")}>
                        <Link className="nav-link" to="/web/feeds">Feeds</Link>
                    </li>
                    {/* <li className={`nav-item ${currPath == '/followers' ? 'active': ''}`}  onClick={() => navigate("/followers")}>
                        <Link className="nav-link" to="/web/followers">Followers</Link>
                    </li>
                    <li className={`nav-item ${currPath == '/following' ? 'active': ''}`}  onClick={() => navigate("/following")}>
                        <Link className="nav-link" to="/web/following">Following</Link>
                    </li> */}
                    <li className={`nav-item ${currPath == '/users' ? 'active': ''}`}  onClick={() => navigate("/users")}>
                        <Link className="nav-link" to="/web/users">Users</Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
}
 
export default NavBar
