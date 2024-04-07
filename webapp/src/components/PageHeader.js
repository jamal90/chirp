import logo from './../logo.svg';

const PageHeader = () => {
    return (
        <nav className="navbar navbar-dark bg-dark text-light">
            <a href="#" className="navbar-brand">
                <img src={logo} />
                Chirp
            </a>
            <form className=" col-lg-4 form-d-inline">
                <input className="form-control bg-dark text-white border-secondary" type="search" placeholder="Search" aria-label="Search" />
            </form>
            <div className="h3">
                <a href="#">
                    <i className="bi bi-box-arrow-right text-light"></i>
                </a>
            </div>
        </nav>
    );
}

export default PageHeader;