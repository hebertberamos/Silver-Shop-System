import DefaultHeader from "../../Components/Header"
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

            </div>
        </div>
    )
}

export default Home
