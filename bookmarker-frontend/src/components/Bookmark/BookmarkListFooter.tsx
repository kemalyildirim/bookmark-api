"use client";

interface FooterProps {
  totalPages: number;
  totalElements: number;
  currentPage: number;
}
const BookmarkListFooter = ({
  totalPages,
  totalElements,
  currentPage,
}: FooterProps) => {
  return (
    <nav className="" aria-label="...">
      <ul className="pagination ml-2">
        <li className={`page-item ${currentPage <= 1 ? "disabled" : ""}`}>
          <span className="page-link">Previous</span>
        </li>
        {Array.from({ length: totalPages }, (_, i: number) => {
          i++;
          console.log("i: " + i);
          return (
            <li
              className={`page-item ${currentPage == i ? "active" : ""}`}
              key={i}
            >
              <a className="page-link" href="#">
                {i}
              </a>
            </li>
          );
        })}
        <li
          className={`page-item ${currentPage >= totalPages ? "disabled" : ""}`}
        >
          <a className="page-link" href="#">
            Next
          </a>
        </li>
      </ul>
    </nav>
  );
};

export default BookmarkListFooter;
