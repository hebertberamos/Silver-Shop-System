import { useEffect, useState} from "react"
import style from './AllProducts.module.css'
import ProductCard from "../product/ProductCard"


function AllProductsCardContainer() {


    const [products, setProducts] = useState([])

    useEffect(() => {

        // const allProducts = async () => {
        //     const response = await fetch('http://localhost:8080/product/all')
        //     const data = await response.json()
        //     setProducts(data.content)
        //     console.log(data.content)
        // }
        // allProducts()

        fetch('http://localhost:8080/product/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        })
        .then((resp) => resp.json()) //transforma resposta em JSON pra poder ser utilizada
        .then((data) => {
            setProducts(data.content)
            console.log(data.content)
        })
        .catch((err) => console.log(err))

    }, []) 

    return (
        <div className={style.allProductsCard}>  
            {products.length > 0 ? (
                products.map( prod => 
                    <ProductCard key={prod.id} mainImageUrl={prod.mainImageUrl} name={prod.name} price={prod.price} />
                )
            ) : (
                <div>
                    <h1>Nenhum produto encontrado</h1>
                </div>
            )}
        </div>
    )
}

export default AllProductsCardContainer