using Microsoft.EntityFrameworkCore.Migrations;

namespace Lochat.Infrastructure.Migrations
{
    public partial class chatroom_range : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "RangeInKilometers",
                table: "Chatrooms",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "RangeInKilometers",
                table: "Chatrooms");
        }
    }
}
