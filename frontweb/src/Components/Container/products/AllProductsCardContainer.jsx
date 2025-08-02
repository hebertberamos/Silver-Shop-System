import { useState, useEffect } from "react"

function AllProductsCardContainer() {

    const [products, setProducts] = useState([])

    useEffect(() => {

        const allProducts = async () => {
            const response = await fetch('http://localhost:8080/product/all')
            const data = await response.json()
            setProducts(data.content)
        }
        allProducts()

    }, []) 

    return (
        products.length > 0 ? (
            products.map( prod => 
                <div key={prod.id} className="producCard">
                    <h2>{prod.name}</h2>
                    <p>{prod.price}</p>
                </div>
            )
        ) : (
            <div>
                <h1>Nenhum produto encontrado</h1>
            </div>
        )
    )
}

export default AllProductsCardContainer