package groovy.bbcode

import groovy.util.GroovyTestCase

import groovy.bbcode.BBCodeBuilder

class BBCodeBuilderTests extends GroovyTestCase
{
    BBCodeBuilder builder

    void setUp()
    {
        builder = new BBCodeBuilder()
    }

    void testDefault()
    {
        assert(true)
    }

    void testBold()
    {
        assertEquals('[b]foo[/b]', builder.bold('foo'))
        assertEquals('[b]foo[/b]', builder.b('foo'))
    }

    void testItalics()
    {
        assertEquals('[i]foo[/i]', builder.italics('foo'))
        assertEquals('[i]foo[/i]', builder.ital('foo'))
        assertEquals('[i]foo[/i]', builder.i('foo'))
    }

    void testStrikethrough()
    {
        assertEquals('[s]foo[/s]', builder.strikethrough('foo'))
        assertEquals('[s]foo[/s]', builder.strike('foo'))
        assertEquals('[s]foo[/s]', builder.s('foo'))
    }

    void testUnderline()
    {
        assertEquals('[u]foo[/u]', builder.underline('foo'))
        assertEquals('[u]foo[/u]', builder.ul('foo'))
        assertEquals('[u]foo[/u]', builder.u('foo'))
    }

    void testUrl()
    {
        assertEquals('[url]foo[/url]', builder.url('foo'))
        assertEquals('[url="foo"]bar[/url]', builder.url('foo', 'bar'))
    }

    void testColors()
    {
        assertEquals(
            '[color="#FF0000"]foo[/color]',
            builder.color('#FF0000', 'foo')
        )

        assertEquals('[color="red"]foo[/color]', builder.red('foo'))
        assertEquals(
            '[color="orange"]foo[/color]', builder.orange('foo'))
        assertEquals(
            '[color="yellow"]foo[/color]', builder.yellow('foo'))
        assertEquals('[color="green"]foo[/color]', builder.green('foo'))
        assertEquals('[color="blue"]foo[/color]', builder.blue('foo'))
        assertEquals(
            '[color="purple"]foo[/color]', builder.purple('foo'))
        assertEquals('[color="black"]foo[/color]', builder.black('foo'))
        assertEquals('[color="white"]foo[/color]', builder.white('foo'))
    }

    void testCenter()
    {
        assertEquals('[center]foo[/center]', builder.center('foo'))
    }

    void testSize()
    {
        assertEquals('[size=10]foo[/size]', builder.size(10, 'foo'))
        assertEquals('[size=999]foo[/size]', builder.size(999, 'foo'))
    }

    void testQuote()
    {
        String sampleQuote = """\
            |Four score and seven years ago our fathers brought forth on this continent a new nation, conceived in liberty, and dedicated to the proposition that all men are created equal.

            |Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battlefield of that war. We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this.

            |But, in a larger sense, we can not dedicate, we can not consecrate, we can not hallow this ground. The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us—that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion—that we here highly resolve that these dead shall not have died in vain—that this nation, under God, shall have a new birth of freedom—and that government of the people, by the people, for the people, shall not perish from the earth.""".stripMargin()

        assertEquals("[quote]${sampleQuote}[/quote]", builder.quote(sampleQuote))
    }

    void testCode()
    {
        String code = "builder.code('builder.code(\'foo\')')"
        assertEquals("[code]${code}[/code]", builder.code(code))
    }

    void testList()
    {
        assertEquals("[list][*]foo[*]bar[*]baz[/list]", builder.list("foo", "bar", "baz"))
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

    void testBuildStringSimple()
    {
        Date date = new Date()

        String expected =
            "[b]${date}[/b]"

        String build = builder.build {
            b(new Date())
        }

        assertEquals(expected, build)
    }

    void testBuildStringMedium()
    {
        String expected =
            '[b]Hello[/b] [i][color="red"]Wor[/color][/i]ld!'

        String build = builder.build {
            "${b('Hello')} ${i(red('Wor'))}ld!"
        }

        assertEquals(expected, build)
    }

    void testBuildStringDocstring()
    {
        String expected = """\
            [b]This[/b] is my docstring.

            [i]It[/i] is [color="green"]formatted[/color] with
            [i]BBCode[/i], made from the
            [u][b][color="blue"]BBCodeBuilder[/color][/b][/u].\
        """

        String build = builder.build { """\
            ${b('This')} is my docstring.

            ${i('It')} is ${green('formatted')} with
            ${i('BBCode')}, made from the
            ${u(b(blue('BBCodeBuilder')))}.\
        """
        }

        assertEquals(expected, build)
    }
}
