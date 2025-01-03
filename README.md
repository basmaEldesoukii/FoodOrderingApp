# Android Shopping App

This Android application allows users to browse categories, view products, and add products to the cart. It integrates with mock APIs to fetch data and stores it locally using Room. 

## Technologies Used
- **KTOR**: API calls
- **KOIN**: Dependency injection
- **Room**: Local database storage
- **Material 3**: UI components
- **Jetpack Compose**: UI development
- **MVI**: Architectural pattern
- **Clean Architecture**: Layered architecture with separation of concerns
- **Multi-Modules**: Modular project structure
- **Coroutines, and Flow**: Asynchronous programming
- **Kotlin**: 100% Language covered

## Features
1. **Search Products**  
   - Search for products by name based on its category.
   - Filters products based on the entered text.

2. **Order Management**  
   - Add products to cart directly from the product list.
   - View total price and quantity of products in the cart.
   - A quantity badge appears on the product item, showing the quantity added to the cart, and increments with each click.
   - Clear cart when pressing "View Order".

3. **API Integration**  
   - Fetch categories and products from a mock API (Mockaroo).
   - Using KTOR
  
4. **Local Data Storage**  
   - Room database is used to save and manage data locally.

5. **UI Screens**  
- **Categories and Products Screens**:  
     - Categories are displayed in dynamic tabs, with each tab representing a category.  
     - Products are displayed in a grid view with details like name, description, price, image, and quantity.  
- **Navigation Bar**: Bottom navigation for Orders, Menu, and Settings screens.
- **Placeholder Screens**: Menu, Orders, and Settings screens.

6. **Responsive Design**  
   - Optimized for portrait mode with UI adjustments for different screen sizes.
     
## Screenshots
<img src="https://github.com/user-attachments/assets/bf0cab6c-8504-4dd5-ade4-197980d1fbee" width="400"/>
<img src="https://github.com/user-attachments/assets/da15f31f-6f27-4b4f-9224-eb1664770957" width="400"/>
<img src="https://github.com/user-attachments/assets/d737eeda-d4fa-4750-b013-c1bc58868d3a" width="400"/>
