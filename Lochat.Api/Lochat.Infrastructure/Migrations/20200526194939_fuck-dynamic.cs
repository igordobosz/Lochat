using Microsoft.EntityFrameworkCore.Migrations;

namespace Lochat.Infrastructure.Migrations
{
    public partial class fuckdynamic : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "IsDynamic",
                table: "Chatrooms");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "IsDynamic",
                table: "Chatrooms",
                type: "tinyint(1)",
                nullable: false,
                defaultValue: false);
        }
    }
}
