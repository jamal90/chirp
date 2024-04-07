import logo from './../logo.svg';

const PageHeader = () => {
    return (
        <nav className="navbar navbar-light bg-light justify-content-between">
            <a href="#" className="navbar-brand">
                <img src={logo}/>
                Chirp
            </a>
            <form className="form-d-inline">
                <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" />
                {/* <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> */}
            </form>  
        </nav>
      );
}
 
export default PageHeader;