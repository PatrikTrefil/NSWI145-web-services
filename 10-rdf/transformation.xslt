<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:output method="text" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <!-- RDF Turtle prefix definitions -->
        <xsl:text>@prefix xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt; .&#10;</xsl:text>
        <xsl:text>@prefix exInvoice: &lt;http://example.com/invoice/&gt; .&#10;</xsl:text>
        <xsl:text>@prefix schema: &lt;https://schema.org/&gt; .&#10;&#10;</xsl:text>
        
        
        <!-- Processing each invoice -->
        <xsl:apply-templates select="invoices/invoice"/>
    </xsl:template>
    
    <xsl:template match="invoice">
        <!-- RDF Description for each invoice -->
        <xsl:text>exInvoice:</xsl:text>
        <xsl:value-of select="id"/>
        <xsl:text> a schema:Invoice ;&#10;</xsl:text>
        <xsl:text>    schema:price "</xsl:text>
        <xsl:value-of select="amountInEur"/>
        <xsl:text>"^^xsd:decimal ;&#10;</xsl:text>
        <xsl:text>    schema:priceCurrency "EUR" .&#10;</xsl:text>
    </xsl:template>
    
</xsl:stylesheet>
