import DefaultHeader from "../../Components/Header"
<<<<<<< Updated upstream
import AllProductsCardContainer from "../../Components/Container/products/AllProductsCardContainer"

function Home() {
    return (
        <div>
            < DefaultHeader />
            <div className="homeImagesPainel">
                
            </div>
            <div className="homeNewsField">
                <AllProductsCardContainer />
            </div>
            <div className="homeCategoryField">
=======
import AllProductsCardContainer from "../../Components/Container/products/allProducts/AllProductsCardContainer"
import Style from './Home.module.css'

function Home() {
    return (
        <div className={Style.homePage}>
            < DefaultHeader />
            <div className={Style.photosPainel}>
                
            </div>
            <div className={Style.newsField}>
                <AllProductsCardContainer />
            </div>
            <div className={Style.categoriesField}>
>>>>>>> Stashed changes

            </div>
        </div>
    )
}

export default Home
