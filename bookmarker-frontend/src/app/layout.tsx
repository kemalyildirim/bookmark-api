import Navbar from "@/components/Navbar";
import { ColorPalette } from "@/components/Theme";
import "bootstrap/dist/css/bootstrap.min.css";

export const metadata = {
  title: "Bookmarker",
  description: "Bookmarker app",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body style={{ backgroundColor: ColorPalette.DarkTheme.backgroundColor }}>
        <Navbar items={[{ href: "add-bookmark", name: "Add Bookmark" }]} />
        {children}
      </body>
    </html>
  );
}
