- customers

  - id: integer
  - first_name: string
  - last_name: string
  - email: string
  - address: string
  - zipcode: string
  - city: string
  - avatar: string
  - birthday: date
  - first_seen: date
  - last_seen: date
  - has_ordered: boolean
  - latest_purchase
  - has_newsletter: boolean
  - groups: array
  - nb_commands: integer
  - total_spent: integer

- categories

  - id: number
  - name: string

- products

  - id: integer
  - category_id: integer
  - reference: string
  - width: float
  - height: float
  - price: float
  - thumbnail: string
  - image: string
  - description: string
  - stock: integer

- commands

  - id: integer
  - reference: string
  - date: date
  - customer_id: integer
  - basket: [{ product_id: integer, quantity: integer }]
  - total_ex_taxes: float
  - delivery_fees: float
  - tax_rate: float
  - taxes: float
  - total: float
  - status: 'ordered' | 'delivered' | 'canceled'
  - returned: boolean

- invoices

  - id: integer
  - date: date
  - command_id: integer
  - customer_id: integer
  - total_ex_taxes: float
  - delivery_fees: float
  - tax_rate: float
  - taxes: float
  - total: float

- reviews
  - id: integer
  - date: date
  - status: 'pending' | 'accepted' | 'rejected'
  - command_id: integer
  - product_id: integer
  - customer_id: integer
  - rating: integer
  - comment: string
