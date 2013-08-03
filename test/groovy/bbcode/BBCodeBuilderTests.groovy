package groovy.bbcode

import groovy.util.GroovyTestCase

import groovy.bbcode.BBCodeBuilder

class ConfigClient01Tests extends GroovyTestCase
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

    void testTableByListParam()
    {
        String expected = "[table][tr][td]one[/td][td]two[/td][td]three[/td][/tr][/table]"

        List tableMatrix = [
            ["one", "two", "three"]
        ]

        assertEquals(expected, builder.table(tableMatrix))
    }

    void testTableByBuilder()
    {
        String expected = "[table][tr][td]one[/td][td]two[/td][td]three[/td][/tr][/table]"

        String table = builder.table {
            tableMatrix = [
                ["one", "two", "three"]
            ]
        }

        assertEquals(expected, table)
    }

    void testBuildingNestedTables()
    {
        String expected = "" +
            "[table]" +
            "[tr][td]one[/td][td]two[/td][td]three[/td][/tr]" +
            "[tr][td]" +
                "[table][tr][td]four[/td][td]five[/td][/tr][/table]" +
            "[/td][/tr]" +
            "[/table]"

        String table = builder.table {
            tableMatrix = [
                ["one", "two", "three"],
                [
                    table {
                        tableMatrix = [
                            ["four", "five"]
                        ]
                    }
                ],
            ]
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
            tableMatrix = [
                [b("one"), i("two"), u("three")],
                [
                    table {
                        tableMatrix = [
                            [
                                url('https://www.google.com/', "four"),
                                b(white("five"))
                            ]
                        ]
                    }
                ],
            ]
        }

        assertEquals(expected, table)
    }
}
