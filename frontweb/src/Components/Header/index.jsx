import { Link } from "react-router-dom"
import style from './Header.module.css'
import searchIcon from '../../assets/icons/search-icon.svg'
import cartIcon from '../../assets/icons/shopping-cart.svg'
import userIcon from '../../assets/icons/userIcon.svg'

function DefaultHeader() {
    return (
        
        <div className={style.defaultHeader}>
           
            <h1>HevaPratas</h1>  
           
            <div className={style.navLinks} >
               
                <nav className={style.navStr}>
                    <Link to="/new_product">Favoritos</Link>

                    <div className={style.dropdown}>
                        <Link to="#">Categorias</Link>

                        <div className={style.dropdownMenu}>
                            <Link to="#">Pulseiras</Link>
                            <Link to="#">Cordões</Link>
                            <Link to="#">Brincos</Link>
                            <Link to="#">Aneis</Link>
                        </div>

                    </div>

                </nav>
                
                <nav className={style.navFavicon}>
                    <Link><img src={searchIcon} alt="search-icon" /></Link>
                    <Link><img src={cartIcon} alt="Shopping cart" /></Link>
                    <Link  to="/user" ><img src={userIcon} alt="User icon" /></Link>
                </nav>
            </div>
        </div>
    )
}

export default DefaultHeader