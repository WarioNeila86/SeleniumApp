Feature: Declined payment at ryanair.com

Scenario: Flight payment using an invalid credit card number
	Given I make a flight search from "DUB" to "SXF" for one adult 10 days after today
	And I select first available flight with standard fare
	And I login to Ryanair site using username "l598328@mvrht.net" and password "Ryanairtest1"
	And I enter payment details with card number "5555555555555557", expiration month "10", expiration year "2018" and security code "265"
	When I complete payment
	Then I get payment declined message