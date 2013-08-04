package groovy.bbcode

import groovy.util.GroovyTestCase

import groovy.bbcode.BBCodeTableBuilder

class BBCodeTableBuilderTests extends GroovyTestCase
{
    BBCodeTableBuilder builder

    void setUp()
    {
        builder = new BBCodeTableBuilder()
    }

    void testDefault()
    {
        assert(true)
    }

    void testTableByListParam()
    {
        String expected =
            "[table]" +
                "[tr]" +
                    "[td]one[/td][td]two[/td][td]three[/td]" +
                "[/tr]" +
            "[/table]"

        List tableMatrix = [
            ["one", "two", "three"]
        ]

        assertEquals(expected, builder.table(tableMatrix))
    }

    void testTableByBuilder()
    {
        String expected =
            "[table]" +
                "[tr]" +
                    "[td]one[/td][td]two[/td][td]three[/td]" +
                "[/tr]" +
            "[/table]"

        String table = builder.table {
            tr("one", "two", "three")
        }

        assertEquals(expected, table)
    }

    void testBuildingNestedTables()
    {
        String expected = "" +
            "[table]" +
                "[tr][td]one[/td][td]two[/td][td]three[/td][/tr]" +
                "[tr][td]" +
                    "[table]" +
                        "[tr][td]four[/td][td]five[/td][/tr]" +
                    "[/table]" +
                "[/td][/tr]" +
            "[/table]"

        String table = builder.table {
            tr("one", "two", "three")
            tr(
                subTable {
                    tr("four", "five")
                }
            )
        }

        assertEquals(expected, table)
    }

    void testBuildingTablesWithAdditionalMarkup()
    {
        String expected = "" +
            "[table]" +
            "[tr]" +
                "[td][b]one[/b][/td]" +
                "[td][i]two[/i][/td]" +
                "[td][u]three[/u][/td]" +
            "[/tr]" +
            "[tr]" +
                "[td]" +
                    "[table]" +
                        "[tr]" +
                            '[td]' +
                                '[url="https://www.google.com/"]' +
                                    'four' +
                                '[/url]' +
                            '[/td]' +
                            '[td]' +
                                '[b][color="white"]five[/color][/b]' +
                            '[/td]' +
                        "[/tr]" +
                    "[/table]" +
                "[/td]" +
            "[/tr]" +
            "[/table]"

        String table = builder.table {
            tr(b("one"), i("two"), u("three"))
            tr(
                subTable {
                    tr (
                        url('https://www.google.com/', "four"),
                        b(white("five"))
                    )
                }
            )
        }

        assertEquals(expected, table)
    }

    void testComplex() {
        String expected =
            "[table]" +
                "[tr][td][b]Name[/b][/td]" +
                    "[td]" +
                        "[table]" +
                            "[tr]" +
                                "[td][b]First[/b][/td]" +
                                "[td]Mary[/td]" +
                            "[/tr]" +
                            "[tr]" +
                                "[td][b]Middle[/b][/td]" +
                                "[td]Jane[/td]" +
                            "[/tr]" +
                            "[tr]" +
                                "[td][b]Last[/b][/td]" +
                                "[td]Doe[/td]" +
                            "[/tr]" +
                        "[/table]" +
                    "[/td]" +
                "[/tr]" +
                "[tr][td][b]Age[/b][/td][td]22[/td][/tr]" +
                "[tr][td][b]Gender[/b][/td][td]Female[/td][/tr]" +
                "[tr]" +
                    "[td][b]Favorite Meal[/b][/td]" +
                    "[td]" +
                        "[table]" +
                            "[tr]" +
                                "[td][b]Breakfast[/b][/td]" +
                                "[td][b]Lunch[/b][/td]" +
                                "[td][b]Dinner[/b][/td]" +
                            "[/tr]" +
                            "[tr]" +
                                "[td]Bagels[/td]" +
                                "[td]Salad[/td]" +
                                "[td]Chicken Cassarole[/td]" +
                            "[/tr]" +
                        "[/table]" +
                    "[/td]" +
                "[/tr]" +
                "[tr][td][b]Name[/b][/td]" +
                    "[td]" +
                        "[table]" +
                            "[tr]" +
                                "[td][b]First[/b][/td]" +
                                "[td]John[/td]" +
                            "[/tr]" +
                            "[tr]" +
                                "[td][b]Middle[/b][/td]" +
                                "[td]Robert[/td]" +
                            "[/tr]" +
                            "[tr]" +
                                "[td][b]Last[/b][/td]" +
                                "[td]Doe[/td]" +
                            "[/tr]" +
                        "[/table]" +
                    "[/td]" +
                "[/tr]" +
                "[tr][td][b]Age[/b][/td][td]22[/td][/tr]" +
                "[tr][td][b]Gender[/b][/td][td]Male[/td][/tr]" +
                "[tr]" +
                    "[td][b]Favorite Meal[/b][/td]" +
                    "[td]" +
                        "[table]" +
                            "[tr]" +
                                "[td][b]Breakfast[/b][/td]" +
                                "[td][b]Lunch[/b][/td]" +
                                "[td][b]Dinner[/b][/td]" +
                            "[/tr]" +
                            "[tr]" +
                                "[td]Eggs & Bacon[/td]" +
                                "[td]Turkey Sandwich[/td]" +
                                "[td]Hamburger[/td]" +
                            "[/tr]" +
                        "[/table]" +
                    "[/td]" +
                "[/tr]" +
            "[/table]"

        List<Map> people = [
            [
                name: [
                    first: "Mary",
                    middle: "Jane",
                    last: "Doe"
                ],
                age: 22,
                gender: "Female",
                favoriteMeal: [
                    breakfast: "Bagels",
                    lunch: "Salad",
                    dinner: "Chicken Cassarole"
                ]
            ],
            [
                name: [
                    first: "John",
                    middle: "Robert",
                    last: "Doe"
                ],
                age: 22,
                gender: "Male",
                favoriteMeal: [
                    breakfast: "Eggs & Bacon",
                    lunch: "Turkey Sandwich",
                    dinner: "Hamburger"
                ]
            ]
        ]

        String table = builder.table {
            people.each { person ->
                tr(
                    b('Name'),
                    subTable {
                        tr(b('First'), person.name.first)
                        tr(b('Middle'), person.name.middle)
                        tr(b('Last'), person.name.last)
                    }
                )
                tr(b('Age'), person.age)
                tr(b('Gender'), person.gender)
                tr(
                    b('Favorite Meal'),
                    subTable {
                        tr(b('Breakfast'), b('Lunch'), b('Dinner'))
                        tr(
                            person.favoriteMeal.breakfast,
                            person.favoriteMeal.lunch,
                            person.favoriteMeal.dinner
                        )
                    }
                )
            }
        }

        assertEquals(expected, table)
    }
}
