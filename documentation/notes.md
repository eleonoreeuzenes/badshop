# Structure

Everything seems static which is not very java idiomatic.

Lots of dependencies between class managed at each class level.

The main loop could interact with a facade.

The `DataStore` seems to be the main state of the app, it is used like an eager singleton but all fields are static, we could make that a real singleton.

`Product` and `Customer` are pojo, `Order` mostly is.
They could be more encapsulated, especially with package level privacy, but since they are pojo it might not be pertinent.

We could put `DataStore`, `Product`, `Customer` and `Order` in a separate package, they all only manage state,.

Creation logic is scattered all over the place, the `DataStore` or some factory along it should handle the pojos creation, (especially since they need to be referenced in the `DataStore` on creation)

The user interaction logic is scattered, particulary the `OrderManager` which handle interactive order creation

# Logic

## Order.java

`productIds` and `quantities` form a SoA (Structure of Array), this seems unnecessary complexity, we should probably just have a array of tuple.
The manual allocation is also questionable..., `ArrayList` is the way to go.
It also handle the reuse of empty slots, but removing an element of an `ArrayList` is not that bad, this is clearly premature optimisation.

`computeTotalRaw` could be a visitor but probably not worse the complexity.

## DiscountCalculator.java

Please no single letter variable...

The discount rules might be separated and applied one by one for more flexibility.

Like a `DiscountRule` interface with a `VrHeadsetDiscountRule`, ...
Or just divide the rules into private methods for simplicity.

## OrderManager.java

`listProducts` and `createCustomerQuick` have nothing to do here...

`createOrderInteractive` and `printOrderDetails` manages both user interaction and business logic.

## ReportGenerator.java

The report generation could be file format independant with an `OrderSerializer` but this is currently not pertinent since there is only one format.

## Utils.java

Maybe could be `FormatUtils` since it only handles format things.

`df` should be `priceDecimalFormat`.

## NotificationService.java

Their could be a `NotificationBackend` interface abstracting the actual specific logic, if it wasn't just `println`...

Also the `Utils` class has method to check email and phone number, their should also be a function to verify if a `Customer` is VIP.

# Proposed structure

- Main
- FormatUtils
- state/
  - Store
  - Product
  - Customer
  - Order
  - ...either factories or the store handle the creation, given the complexity probably not worth it
- ui
  - MainMenu (with just a static method ?), interacting with the facade (or an interface of the facade) and the other ui components
  - OrderCreationForm (with just a static method ?)
  - CustomerCreationForm (with just a static method ?)
- services/
  - StoreFacade
  - DiscountService
  - notification/
    - NotificationService
    - NotificationBackend
    - SmsNotificationBackend
    - EmailNotificationBackend
  - OrderService
  - ReportService # Or a package with a ReportSerializer, TextReportSerializer, ...

TODO: Maybe add a user interface package to handle all the user interactions which are scattered everywhere
