<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"> 
<xsl:template match="/"> <xsl:text>&#xa;</xsl:text>
<entries> <xsl:text>&#xa;</xsl:text>
   <xsl:for-each select="entries/entry">
       <entry>
           <xsl:attribute name="field">
               <xsl:value-of select="field">
	       </xsl:value-of>
           </xsl:attribute>
       </entry> <xsl:text>&#xa;</xsl:text>
   </xsl:for-each>
 </entries>
</xsl:template>
</xsl:stylesheet>