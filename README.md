# BBCodeBuilder

This is a simple Groovy utility for creating BBCode elements.

The primary interface is through the `BBCodeBuilder` Object, which has
many simple methods for generating simple BBCode elements. However, its
real strength comes in the way the `BBCodeTableBuilder` is implemented.

The `BBCodeBuilder` can implement some pretty complex tables that look
visually appealing and intuitive in code, like so:

    String table = new BBCodeBuilder().table {
        tableMatrix = [
            // Table Row
            [
                b("Name"), 
                table {
                    tableMatrix = [
                        
                        // Table rows
                        ["First", "Mary"],
                        [i("Middle"), "Jane"],
                        ["Last", "Doe]
                    ]
                }
            ],

            // Table Rows
            [b("Age"), 22],
            [b("Gender"), yellow("Female")]
            [
                // Table Row
                "Favorite Meal",
                table {
                    tableMatrix = [

                        // Table Row
                        [
                            b(orange("Breakfast")),
                            (s("Pancakes ") + u(b("Eggs & Bacon")))
                        ],

                        // Table Rows
                        ["Lunch", "Salad"],
                        ["Dinner", "Spaghetti & Meatballs"]
                    ]
                }
            ]
        ]
    }

This renders a table like the following (without colors):

<table border="1" width="100%">
  <tr>
    <td><b>Name</b></td>
    <td>
      <table border="1" width="100%">
        <tr>
          <td>First</td><td>Mary</td>
        </tr>
        <tr>
          <td><i>Middle</i></td><td>Jane</td>
        </tr>
        <tr>
          <td>Last</td><td>Doe</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td><b>Age</td><td>22</td>
  </tr>
  <tr>
    <td><b>Gender</b></td><td>Female</td>
  </tr>
  <tr>
    <td><b>Favorite Meal</b></td>
    <td>
      <table border="1" width="100%">
        <tr>
          <td><b>Breakfast</b></td>
          <td><s>Pancakes</s> <b><u>Eggs & Bacon</u></b></td>
        </tr>
        <tr>
          <td>Lunch</td><td>Salad</td>
        </tr>
        <tr>
          <td>Dinner</td><td>Spaghetti & Meatballs</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
